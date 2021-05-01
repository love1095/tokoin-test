/**
 * Copyright © 2018 Interspace Co., Ltd. All rights reserved.
 *
 * Licensed under the Interspace's License,
 * you may not use this file except in compliance with the License.
 */
package com.mycompany.tokointest.models;

/**
 * Base interface for {@link Enum}s containing an integer value. Helps the mapping of such
 * {@link Enum}s with MyBatis.
 *
 * @author Csaba Varga
 */
public interface ValueEnum {

    /**
     * Returns the value stored in the current element.
     *
     * @return the value stored in the current element.
     */
    int getValue();
}
