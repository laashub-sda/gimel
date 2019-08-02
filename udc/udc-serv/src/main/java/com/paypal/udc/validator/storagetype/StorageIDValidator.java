/*
 * Copyright 2019 PayPal Inc.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.paypal.udc.validator.storagetype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.paypal.udc.entity.storagetype.StorageType;
import com.paypal.udc.exception.ValidationError;
import com.paypal.udc.util.StorageUtil;


@Component
public class StorageIDValidator implements StorageTypeValidator {

    private StorageTypeValidator chain;

    @Autowired
    private StorageUtil storageUtil;

    @Override
    public void setNextChain(final StorageTypeValidator nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void validate(final StorageType storageType, final StorageType updatedStorageType)
            throws ValidationError {
        if (storageType.getStorageId() > 0) {
            this.storageUtil.validateStorageId(storageType.getStorageId());
            updatedStorageType.setStorageId(storageType.getStorageId());
        }
    }
}
