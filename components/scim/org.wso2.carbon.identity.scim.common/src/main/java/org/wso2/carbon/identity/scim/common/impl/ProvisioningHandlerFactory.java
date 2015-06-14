/*
 * Copyright (c) 2010, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.scim.common.impl;

import org.wso2.charon.core.provisioning.ProvisioningHandler;

/**
 * org.wso2.carbon.identity.scim.common.api.ProvisioningHandler can be used as an extension
 * point to plug in ProvisioningHandlers. This class is responsible for creating and providing
 * relevant ProvisioningHandler objects, reading from config files.
 */
public class ProvisioningHandlerFactory {

    public ProvisioningHandler getUserProvisioningHandler() {
        return null;
    }

    public ProvisioningHandler getGroupProvisioningHandler() {
        return null;
    }

}
