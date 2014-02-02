/*
 * Copyright (C) 2011 Clearspring Technologies, Inc. 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.clearspring.analytics.stream.cardinality;

import java.io.IOException;
import java.nio.ByteBuffer;


public interface ICardinality
{
    /**
     * @param o stream element
     * @return false if the value returned by cardinality() is unaffected by the appearance of o in the stream.
     */
    boolean offer(Object o);

    /**
     * Offer the value as a hashed long value
     *
     * @param hashedLong - the hash of the item to offer to the estimator
     * @return false if the value returned by cardinality() is unaffected by the appearance of hashedLong in the stream
     */
    boolean offerHashed(long hashedLong);

    /**
     * Offer the value as a hashed long value
     *
     * @param hashedInt - the hash of the item to offer to the estimator
     * @return false if the value returned by cardinality() is unaffected by the appearance of hashedInt in the stream
     */
    boolean offerHashed(int hashedInt);

    /**
     * @return the number of unique elements in the stream or an estimate thereof
     */
    long cardinality();

    /**
     * @return size in bytes needed for serialization
     */
    int sizeof();

    /**
     * @return
     * @throws IOException
     * @deprecated use getBuffer
     */
    byte[] getBytes() throws IOException;

    /**
     * @return
     * @throws IOException
     */
    ByteBuffer getBuffer();

    /**
     * Merges estimators to produce a new estimator for the combined streams
     * of this estimator and those passed as arguments.
     * 
     * Nor this estimator nor the one passed as parameters are modified.
     *
     * @param estimators Zero or more compatible estimators
     * @throws CardinalityMergeException If at least one of the estimators is not compatible with this one
     */
    ICardinality merge(ICardinality... estimators) throws CardinalityMergeException;
}
