package by.neon.tour.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import by.neon.tour.config.WebConfig;

/**
 * @author Nikolay Moskal
 */
public class WebInitializer implements WebApplicationInitializer {
    /**
     * (non-Javadoc)
     *
     * @see org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet.ServletContext)
     */
    @Override
    public void onStartup(ServletContext arg0) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebConfig.class);
        arg0.addListener(new ContextLoaderListener(ctx));
        arg0.addFilter("OpenEntityManagerInViewFilter", OpenEntityManagerInViewFilter.class).addMappingForServletNames(null, false, "dispatcher");
        ctx.setServletContext(arg0);
        ServletRegistration.Dynamic servlet = arg0.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }

}
