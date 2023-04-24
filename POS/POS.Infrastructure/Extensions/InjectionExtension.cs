using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using POS.Infrastructure.Persistencias.Context;

namespace POS.Infrastructure.Extensions
{
    public static class InjectionExtension
    {
        public static IServiceCollection AddInjectionInfraestructure(this IServiceCollection services,
            IConfiguration configuration)
        {
            var assembly = typeof(POSContext).Assembly.FullName;

            services.AddDbContext<POSContext>(
                options => options.UseSqlServer(
                    configuration.GetConnectionString("POSConnection"), b => b.MigrationsAssembly(assembly)), ServiceLifetime.Transient);
            return services;
        }
    }
}
