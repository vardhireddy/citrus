/*
 * Copyright 2006-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.consol.citrus.ftp.config.annotation;

import com.consol.citrus.annotations.CitrusEndpointConfig;
import com.consol.citrus.message.ErrorHandlingStrategy;

import java.lang.annotation.*;

/**
 * @author Christoph Deppisch
 * @since 2.5
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@CitrusEndpointConfig(qualifier = "endpoint.parser.sftp.client")
public @interface SftpClientConfig {

    /**
     * Host.
     * @return
     */
    String host() default "localhost";

    /**
     * Server port.
     * @return
     */
    int port() default 22222;

    /**
     * Auto read files
     * @return
     */
    boolean autoReadFiles() default true;

    /**
     * Username
     * @return
     */
    String username() default "";

    /**
     * Password
     * @return
     */
    String password() default "";

    /**
     * PrivateKeyPath.
     * @return
     */
    String privateKeyPath() default "";

    /**
     * <privateKeyPassword.
     * @return
     */
    String privateKeyPassword() default "";

    /**
     * StrictHostChecking.
     * @return
     */
    boolean strictHostChecking() default false;

    /**
     * KnownHosts.
     * @return
     */
    String knownHosts() default "";

    /**
     * Message correlator.
     * @return
     */
    String correlator() default "";

    /**
     * Error handling strategy.
     * @return
     */
    ErrorHandlingStrategy errorStrategy() default ErrorHandlingStrategy.PROPAGATE;

    /**
     * Polling interval.
     * @return
     */
    int pollingInterval() default 500;

    /**
     * Timeout.
     * @return
     */
    long timeout() default 5000L;

    /**
     * Test actor.
     * @return
     */
    String actor() default "";
}
