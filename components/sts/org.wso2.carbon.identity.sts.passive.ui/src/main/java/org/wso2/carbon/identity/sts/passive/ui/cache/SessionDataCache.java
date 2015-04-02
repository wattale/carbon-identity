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

package org.wso2.carbon.identity.sts.passive.ui.cache;

import org.wso2.carbon.identity.application.authentication.framework.store.SessionDataStore;
import org.wso2.carbon.identity.application.common.cache.BaseCache;
import org.wso2.carbon.identity.core.util.IdentityUtil;

public class SessionDataCache extends BaseCache<String, CacheEntry> {

    private static final String SESSION_DATA_CACHE_NAME = "PassiveSTSSessionDataCache";
    private static volatile SessionDataCache instance;
	private boolean enableRequestScopeCache = false;

    private SessionDataCache(String cacheName) {
        super(cacheName);
	    if (IdentityUtil.getProperty("JDBCPersistenceManager.SessionDataPersist.Temporary") != null) {
		    enableRequestScopeCache = Boolean.parseBoolean(IdentityUtil.getProperty("JDBCPersistenceManager.SessionDataPersist.Temporary"));
	    }
    }

    private SessionDataCache(String cacheName, int timeout) {
        super(cacheName, timeout);
    }

    public static SessionDataCache getInstance(int timeout) {
        if (instance == null) {
            synchronized (SessionDataCache.class) {

                if (instance == null) {
                    instance = new SessionDataCache(SESSION_DATA_CACHE_NAME, timeout);
                }
            }
        }
        return instance;
    }

	public void addToCache(CacheKey key, CacheEntry entry){
		String keyValue = ((SessionDataCacheKey)key).getSessionDataKey();
		super.addToCache(keyValue ,entry);
		SessionDataStore.getInstance().storeSessionData(keyValue,SESSION_DATA_CACHE_NAME,entry);
		if (enableRequestScopeCache) {
			SessionDataStore.getInstance().storeSessionData(keyValue, SESSION_DATA_CACHE_NAME, entry);
		}
	}

	public void clearCacheEntry(CacheKey key){
		String keyValue = ((SessionDataCacheKey)key).getSessionDataKey();
		super.clearCacheEntry(keyValue);
		if (enableRequestScopeCache) {
			SessionDataStore.getInstance().clearSessionData(keyValue, SESSION_DATA_CACHE_NAME);
		}
	}

	public CacheEntry getValueFromCache(CacheKey key) {
		String keyValue = ((SessionDataCacheKey)key).getSessionDataKey();
		CacheEntry cacheEntry = super.getValueFromCache(keyValue);
		if(cacheEntry == null){
			cacheEntry = (CacheEntry) SessionDataStore.getInstance().getSessionData(keyValue,SESSION_DATA_CACHE_NAME);
		}
		return cacheEntry;
	}
}
