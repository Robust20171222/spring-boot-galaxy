package com.galaxy.bigdata.sentry;

import org.apache.sentry.SentryUserException;
import org.apache.sentry.provider.db.service.thrift.SentryPolicyServiceClient;
import org.apache.sentry.provider.db.service.thrift.TSentryRole;
import org.apache.sentry.service.thrift.SentryServiceClientFactory;
import org.junit.Test;

import java.util.Set;

/**
 * @author pengwang
 * @date 2019/06/19
 */
public class SentryClientTest {

    private static SentryConfig sentryConfig = new SentryConfig("/Users/pengwang/Documents/project/test/spring-boot-galaxy/bigdata-galaxy/src/test/scala/com/galaxy/bigdata/sentry/sentry-site-client.xml");

    @Test
    public void testListRoles() throws InternalException {
        SentryServiceClient client = null;
        try {
            client = new SentryServiceClient();
            Set<TSentryRole> roles = client.get().listRoles("hadoop");
            for (TSentryRole role : roles) {
                System.out.println(role);
            }
        } catch (InternalException | SentryUserException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }

    @Test
    public void testDropRoleIfExists() throws InternalException {
        SentryServiceClient client = null;
        try {
            client = new SentryServiceClient();
            client.get().dropRoleIfExists("hadoop","admin_role");
        } catch (InternalException | SentryUserException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }


    /**
     * Wrapper around a SentryPolicyServiceClient.
     * TODO: When SENTRY-296 is resolved we can more easily cache connections instead of
     * opening a new connection for each request.
     */
    static class SentryServiceClient implements AutoCloseable {
        private final SentryPolicyServiceClient client_;

        /**
         * Creates and opens a new Sentry Service thrift client.
         */
        public SentryServiceClient() throws InternalException {
            client_ = createClient();
        }

        /**
         * Get the underlying SentryPolicyServiceClient.
         */
        public SentryPolicyServiceClient get() {
            return client_;
        }

        /**
         * Returns this client back to the connection pool. Can be called multiple times.
         */
        public void close() throws InternalException {
            try {
                client_.close();
            } catch (Exception e) {
                throw new InternalException("Error closing client: ", e);
            }
        }

        /**
         * Creates a new client to the SentryService.
         */
        private SentryPolicyServiceClient createClient() throws InternalException {
            SentryPolicyServiceClient client;
            try {
                sentryConfig.loadConfig();
                client = SentryServiceClientFactory.create(sentryConfig.getConfig());
            } catch (Exception e) {
                throw new InternalException("Error creating Sentry Service client: ", e);
            }
            return client;
        }
    }
}