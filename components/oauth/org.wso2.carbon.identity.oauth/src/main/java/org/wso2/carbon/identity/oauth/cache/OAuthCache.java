/*
*Copyright (c) 2005-2013, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*WSO2 Inc. licenses this file to you under the Apache License,
*Version 2.0 (the "License"); you may not use this file except
*in compliance with the License.
*You may obtain a copy of the License at
*
*http://www.apache.org/licenses/LICENSE-2.0
*
*Unless required by applicable law or agreed to in writing,
*software distributed under the License is distributed on an
*"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
*KIND, either express or implied.  See the License for the
*specific language governing permissions and limitations
*under the License.
*/

package org.wso2.carbon.identity.oauth.cache;

import org.wso2.carbon.identity.application.common.cache.BaseCache;
import org.wso2.carbon.identity.application.common.util.IdentityApplicationConstants;
import org.wso2.carbon.utils.CarbonUtils;

public class OAuthCache extends BaseCache<CacheKey, CacheEntry> {

    private static final String OAUTH_CACHE_NAME = "OAuthCache";

    private static volatile OAuthCache instance;

    private OAuthCache(String cacheName, int timeout) {
        super(cacheName,timeout);
    }

    public static OAuthCache getInstance(int timeout) {
        CarbonUtils.checkSecurity();
	    if (instance == null) {
		    synchronized (SessionDataCache.class) {
			    if (instance == null) {
				    instance = new OAuthCache(OAUTH_CACHE_NAME, timeout);
			    }
		    }
	    }
        return instance;
    }

    @Override
    public void addToCache(CacheKey key, CacheEntry entry) {
        super.addToCache(key, entry);
    }

    @Override
    public CacheEntry getValueFromCache(CacheKey key) {
        return super.getValueFromCache(key);
    }

    @Override
    public void clearCacheEntry(CacheKey key) {
        super.clearCacheEntry(key);
    }
}
