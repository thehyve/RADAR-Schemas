package org.radarcns.specifications.util;

/*
 * Copyright 2017 King's College London and The Hyve
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

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import org.radarcns.kafka.aggregator.AggregatorDouble;
import org.radarcns.kafka.aggregator.AggregatorDoubleArray;

/**
 * TODO.
 */
public final class Utils {

    private static final String GRADLE_PROPERTIES = "exchange.properties";
    private static final String PROPERTY_VALUE = "project.group";
    private static String projectGroup;

    private static final Set<String> TIMED_AGGREGATOR;

    static {
        TIMED_AGGREGATOR = new HashSet<>();
        TIMED_AGGREGATOR.add(AggregatorDouble.class.getCanonicalName());
        TIMED_AGGREGATOR.add(AggregatorDoubleArray.class.getCanonicalName());
    }

    private Utils() {
        //Static class
    }

    /**
     * TODO.
     * @return TODO
     */
    public static String getProjectGroup() {
        if (Objects.isNull(projectGroup)) {
            try {
                Properties prop = new Properties();


                //System.out.println();

                prop.load(
                    ClassLoader.getSystemClassLoader().getResourceAsStream(GRADLE_PROPERTIES));
                projectGroup = prop.getProperty(PROPERTY_VALUE);
            } catch (IOException exc) {
                throw new IllegalStateException(PROPERTY_VALUE.concat(
                    " cannot be extracted from ").concat(GRADLE_PROPERTIES), exc);
            }
        }

        return projectGroup;
        //return "org.radarcns";
    }

    /**
     * TODO.
     * @param aggregator TODO
     * @return TODO
     */
    public static boolean isTimedAggregator(String aggregator) {
        return TIMED_AGGREGATOR.contains(aggregator);
    }

}
