/*
 * Copyright 2006-2010 the original author or authors.
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

package com.consol.citrus.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.channel.interceptor.ChannelInterceptorAdapter;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;

/**
 * @author Christoph Deppisch
 */
public class LoggingInterceptor extends ChannelInterceptorAdapter {
    private static Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);
    
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info(channel.toString() + ": " + message.getPayload());
        
        if (message.getPayload() instanceof Throwable) {
            ((Throwable)message.getPayload()).printStackTrace();
        }
        
        return super.preSend(message, channel);
    }
}
