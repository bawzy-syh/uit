package com.syh.uit.user_connection_center.config.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "ucc.multi-redis-source")
public class MultiSourceRedisProperties {

    private SingleRedisProperties onlineState;

    private SingleRedisProperties messageTemporaryStorage;

    public SingleRedisProperties getOnlineState() {
        return onlineState;
    }

    public SingleRedisProperties getMessageTemporaryStorage() {
        return messageTemporaryStorage;
    }

    public void setOnlineState(SingleRedisProperties onlineState) {
        this.onlineState = onlineState;
    }

    public void setMessageTemporaryStorage(SingleRedisProperties messageTemporaryStorage) {
        this.messageTemporaryStorage = messageTemporaryStorage;
    }

    public static class SingleRedisProperties {

        /**
         * Database index used by the connection factory.
         */
        private int database = 0;

        /**
         * Connection URL. Overrides host, port, and password. User is ignored. Example:
         * redis://user:password@example.com:6379
         */
        private String url;

        /**
         * Redis server host.
         */
        private String host = "localhost";

        /**
         * Login password of the redis server.
         */
        private String password;

        /**
         * Redis server port.
         */
        private int port = 6379;

        /**
         * Whether to enable SSL support.
         */
        private boolean ssl;

        /**
         * Connection timeout.
         */
        private Duration timeout;

        /**
         * Client name to be set on connections with CLIENT SETNAME.
         */
        private String clientName;

        private SingleRedisProperties.Sentinel sentinel;

        private SingleRedisProperties.Cluster cluster;

        private final SingleRedisProperties.Jedis jedis = new SingleRedisProperties.Jedis();

        private final SingleRedisProperties.Lettuce lettuce = new SingleRedisProperties.Lettuce();

        public int getDatabase() {
            return this.database;
        }

        public void setDatabase(int database) {
            this.database = database;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHost() {
            return this.host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getPort() {
            return this.port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public boolean isSsl() {
            return this.ssl;
        }

        public void setSsl(boolean ssl) {
            this.ssl = ssl;
        }

        public void setTimeout(Duration timeout) {
            this.timeout = timeout;
        }

        public Duration getTimeout() {
            return this.timeout;
        }

        public String getClientName() {
            return this.clientName;
        }

        public void setClientName(String clientName) {
            this.clientName = clientName;
        }

        public SingleRedisProperties.Sentinel getSentinel() {
            return this.sentinel;
        }

        public void setSentinel(SingleRedisProperties.Sentinel sentinel) {
            this.sentinel = sentinel;
        }

        public SingleRedisProperties.Cluster getCluster() {
            return this.cluster;
        }

        public void setCluster(SingleRedisProperties.Cluster cluster) {
            this.cluster = cluster;
        }

        public SingleRedisProperties.Jedis getJedis() {
            return this.jedis;
        }

        public SingleRedisProperties.Lettuce getLettuce() {
            return this.lettuce;
        }

        /**
         * Pool properties.
         */
        public static class Pool {

            /**
             * Maximum number of "idle" connections in the pool. Use a negative value to
             * indicate an unlimited number of idle connections.
             */
            private int maxIdle = 8;

            /**
             * Target for the minimum number of idle connections to maintain in the pool. This
             * setting only has an effect if both it and time between eviction runs are
             * positive.
             */
            private int minIdle = 0;

            /**
             * Maximum number of connections that can be allocated by the pool at a given
             * time. Use a negative value for no limit.
             */
            private int maxActive = 8;

            /**
             * Maximum amount of time a connection allocation should block before throwing an
             * exception when the pool is exhausted. Use a negative value to block
             * indefinitely.
             */
            private Duration maxWait = Duration.ofMillis(-1);

            /**
             * Time between runs of the idle object evictor thread. When positive, the idle
             * object evictor thread starts, otherwise no idle object eviction is performed.
             */
            private Duration timeBetweenEvictionRuns;

            public int getMaxIdle() {
                return this.maxIdle;
            }

            public void setMaxIdle(int maxIdle) {
                this.maxIdle = maxIdle;
            }

            public int getMinIdle() {
                return this.minIdle;
            }

            public void setMinIdle(int minIdle) {
                this.minIdle = minIdle;
            }

            public int getMaxActive() {
                return this.maxActive;
            }

            public void setMaxActive(int maxActive) {
                this.maxActive = maxActive;
            }

            public Duration getMaxWait() {
                return this.maxWait;
            }

            public void setMaxWait(Duration maxWait) {
                this.maxWait = maxWait;
            }

            public Duration getTimeBetweenEvictionRuns() {
                return this.timeBetweenEvictionRuns;
            }

            public void setTimeBetweenEvictionRuns(Duration timeBetweenEvictionRuns) {
                this.timeBetweenEvictionRuns = timeBetweenEvictionRuns;
            }

        }

        /**
         * Cluster properties.
         */
        public static class Cluster {

            /**
             * Comma-separated list of "host:port" pairs to bootstrap from. This represents an
             * "initial" list of cluster nodes and is required to have at least one entry.
             */
            private List<String> nodes;

            /**
             * Maximum number of redirects to follow when executing commands across the
             * cluster.
             */
            private Integer maxRedirects;

            public List<String> getNodes() {
                return this.nodes;
            }

            public void setNodes(List<String> nodes) {
                this.nodes = nodes;
            }

            public Integer getMaxRedirects() {
                return this.maxRedirects;
            }

            public void setMaxRedirects(Integer maxRedirects) {
                this.maxRedirects = maxRedirects;
            }

        }

        /**
         * Redis sentinel properties.
         */
        public static class Sentinel {

            /**
             * Name of the Redis server.
             */
            private String master;

            /**
             * Comma-separated list of "host:port" pairs.
             */
            private List<String> nodes;

            public String getMaster() {
                return this.master;
            }

            public void setMaster(String master) {
                this.master = master;
            }

            public List<String> getNodes() {
                return this.nodes;
            }

            public void setNodes(List<String> nodes) {
                this.nodes = nodes;
            }

        }

        /**
         * Jedis client properties.
         */
        public static class Jedis {

            /**
             * Jedis pool configuration.
             */
            private SingleRedisProperties.Pool pool;

            public SingleRedisProperties.Pool getPool() {
                return this.pool;
            }

            public void setPool(SingleRedisProperties.Pool pool) {
                this.pool = pool;
            }

        }

        /**
         * Lettuce client properties.
         */
        public static class Lettuce {

            /**
             * Shutdown timeout.
             */
            private Duration shutdownTimeout = Duration.ofMillis(100);

            /**
             * Lettuce pool configuration.
             */
            private SingleRedisProperties.Pool pool;

            public Duration getShutdownTimeout() {
                return this.shutdownTimeout;
            }

            public void setShutdownTimeout(Duration shutdownTimeout) {
                this.shutdownTimeout = shutdownTimeout;
            }

            public SingleRedisProperties.Pool getPool() {
                return this.pool;
            }

            public void setPool(SingleRedisProperties.Pool pool) {
                this.pool = pool;
            }

        }
    }
}
