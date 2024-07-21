package com.carlos.accounts.service.Impl;

import com.carlos.accounts.constants.StaticResponseHttp;
import com.carlos.accounts.dto.AccountsDto;
import com.carlos.accounts.dto.CustomerDto;
import com.carlos.accounts.entity.Accounts;
import com.carlos.accounts.entity.Customer;
import com.carlos.accounts.exception.CustomerAlreadyException;
import com.carlos.accounts.exception.ResourceNotFoundException;
import com.carlos.accounts.mapper.AccountsMapper;
import com.carlos.accounts.mapper.CustomerMapper;
import com.carlos.accounts.repository.AccountRepository;
import com.carlos.accounts.repository.CustomerRepository;
import com.carlos.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class IAccountsServiceImpl implements IAccountsService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        // Creamos el objeto customer del customerDto que nos envian
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        // Buscamos si el usuario existe por Numero de Telefono
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyException("Customer alredy registered with given mobileNumber "+customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        // Almacenamos el cliente creado
        Customer savedCusteomer = customerRepository.save(customer);
        // Llamamos al metodo crear cuenta y lo guardamos.
        accountRepository.save(createNewAccount(savedCusteomer));
    }

    // Metodo que creara una cuenta nueva. Creara Numero aleatorio de 10 digitos.
    private Accounts createNewAccount(Customer customer){
        Accounts newAccount = new Accounts();
        // Obtenemos el Id del cliente
        newAccount.setCustomerId(customer.getCustomerId());
        // Creamos el numero aleatorio de la cuenta
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(StaticResponseHttp.SAVINGS);
        newAccount.setBranchAddress(StaticResponseHttp.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");
        return newAccount;
    }


    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        // Buscamos el cliente por number movil
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                        ()-> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
                );

        // Si el cliente existe buscamos la cuenta asociada por el id del cliente
        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account","customerId",customer.getCustomerId().toString())
        );

        // Pasamos de Customer a CustomerDto
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        // al DTO le estamos añadiendo la informacion de la cuenta que le pertenece
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }
}
