package org.wheport.ics.filter;

import org.jasig.cas.client.proxy.Cas20ProxyRetriever;
import org.jasig.cas.client.proxy.CleanUpTimerTask;
import org.jasig.cas.client.proxy.ProxyGrantingTicketStorage;
import org.jasig.cas.client.proxy.ProxyGrantingTicketStorageImpl;
import org.jasig.cas.client.util.CommonUtils;
import org.jasig.cas.client.validation.AbstractTicketValidationFilter;
import org.jasig.cas.client.validation.Cas20ProxyTicketValidator;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class EnfoCas20ProxyReceivingTicketValidationFilter extends AbstractTicketValidationFilter {

    private static final String[] RESERVED_INIT_PARAMS = new String[]{"proxyGrantingTicketStorageClass", "proxyReceptorUrl", "acceptAnyProxy", "allowedProxyChains", "casServerUrlPrefix", "proxyCallbackUrl", "renew", "exceptionOnValidationFailure", "redirectAfterValidation", "useSession", "serverName", "service", "artifactParameterName", "serviceParameterName", "encodeServiceUrl", "millisBetweenCleanUps"};
    private static final int DEFAULT_MILLIS_BETWEEN_CLEANUPS = 60000;
    private String proxyReceptorUrl;
    private Timer timer;
    private TimerTask timerTask;
    private int millisBetweenCleanUps;
    private ProxyGrantingTicketStorage proxyGrantingTicketStorage = new ProxyGrantingTicketStorageImpl();

    public EnfoCas20ProxyReceivingTicketValidationFilter() {
    }

    protected void initInternal(FilterConfig filterConfig) throws ServletException {
        this.setProxyReceptorUrl(this.getPropertyFromInitParams(filterConfig, "proxyReceptorUrl", (String)null));
        String proxyGrantingTicketStorageClass = this.getPropertyFromInitParams(filterConfig, "proxyGrantingTicketStorageClass", (String)null);
        if (proxyGrantingTicketStorageClass != null) {
            try {
                Class storageClass = Class.forName(proxyGrantingTicketStorageClass);
                this.proxyGrantingTicketStorage = (ProxyGrantingTicketStorage)storageClass.newInstance();
            } catch (Exception var4) {
                throw new RuntimeException(var4);
            }
        }

        this.log.trace("Setting proxyReceptorUrl parameter: " + this.proxyReceptorUrl);
        this.millisBetweenCleanUps = Integer.parseInt(this.getPropertyFromInitParams(filterConfig, "millisBetweenCleanUps", Integer.toString(60000)));
        super.initInternal(filterConfig);
    }

    public void init() {
        super.init();
        CommonUtils.assertNotNull(this.proxyGrantingTicketStorage, "proxyGrantingTicketStorage cannot be null.");
        if (this.timer == null) {
            this.timer = new Timer(true);
        }

        if (this.timerTask == null) {
            this.timerTask = new CleanUpTimerTask(this.proxyGrantingTicketStorage);
        }

        this.timer.schedule(this.timerTask, (long)this.millisBetweenCleanUps, (long)this.millisBetweenCleanUps);
    }

    protected final TicketValidator getTicketValidator(FilterConfig filterConfig) {
        String allowAnyProxy = this.getPropertyFromInitParams(filterConfig, "acceptAnyProxy", (String)null);
        String allowedProxyChains = this.getPropertyFromInitParams(filterConfig, "allowedProxyChains", (String)null);
        String casServerUrlPrefix = this.getPropertyFromInitParams(filterConfig, "casServerUrlPrefix", (String)null);
        Object validator;
        if (!CommonUtils.isNotBlank(allowAnyProxy) && !CommonUtils.isNotBlank(allowedProxyChains)) {
            validator = new Cas20ServiceTicketValidator(casServerUrlPrefix);
        } else {
            Cas20ProxyTicketValidator v = new Cas20ProxyTicketValidator(casServerUrlPrefix);
            v.setAcceptAnyProxy(this.parseBoolean(allowAnyProxy));
            v.setAllowedProxyChains(CommonUtils.createProxyList(allowedProxyChains));
            validator = v;
        }

        ((Cas20ServiceTicketValidator)validator).setProxyCallbackUrl(this.getPropertyFromInitParams(filterConfig, "proxyCallbackUrl", (String)null));
        ((Cas20ServiceTicketValidator)validator).setProxyGrantingTicketStorage(this.proxyGrantingTicketStorage);
        ((Cas20ServiceTicketValidator)validator).setProxyRetriever(new Cas20ProxyRetriever(casServerUrlPrefix, this.getPropertyFromInitParams(filterConfig, "encoding", (String)null)));
        ((Cas20ServiceTicketValidator)validator).setRenew(this.parseBoolean(this.getPropertyFromInitParams(filterConfig, "renew", "false")));
        ((Cas20ServiceTicketValidator)validator).setEncoding(this.getPropertyFromInitParams(filterConfig, "encoding", (String)null));
        Map additionalParameters = new HashMap();
        List params = Arrays.asList(RESERVED_INIT_PARAMS);
        Enumeration e = filterConfig.getInitParameterNames();

        while(e.hasMoreElements()) {
            String s = (String)e.nextElement();
            if (!params.contains(s)) {
                additionalParameters.put(s, filterConfig.getInitParameter(s));
            }
        }

        ((Cas20ServiceTicketValidator)validator).setCustomParameters(additionalParameters);
        ((Cas20ServiceTicketValidator)validator).setHostnameVerifier(this.getHostnameVerifier(filterConfig));
        return (TicketValidator)validator;
    }

    public void destroy() {
        super.destroy();
        this.timer.cancel();
    }

    protected final boolean preFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String requestUri = request.getRequestURI();
        if (!CommonUtils.isEmpty(this.proxyReceptorUrl) && requestUri.endsWith(this.proxyReceptorUrl)) {
            try {
                CommonUtils.readAndRespondToProxyReceptorRequest(request, response, this.proxyGrantingTicketStorage);
                return false;
            } catch (RuntimeException var8) {
                this.log.error(var8.getMessage(), var8);
                throw var8;
            }
        } else {
            return true;
        }
    }

    public final void setProxyReceptorUrl(String proxyReceptorUrl) {
        this.proxyReceptorUrl = proxyReceptorUrl;
    }

    public void setProxyGrantingTicketStorage(ProxyGrantingTicketStorage storage) {
        this.proxyGrantingTicketStorage = storage;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void setTimerTask(TimerTask timerTask) {
        this.timerTask = timerTask;
    }

    public void setMillisBetweenCleanUps(int millisBetweenCleanUps) {
        this.millisBetweenCleanUps = millisBetweenCleanUps;
    }
}
