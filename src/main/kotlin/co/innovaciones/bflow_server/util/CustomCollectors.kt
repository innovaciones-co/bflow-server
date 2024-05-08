package co.innovaciones.bflow_server.util

import java.lang.IllegalStateException
import java.util.LinkedHashMap
import java.util.function.Function
import java.util.stream.Collector
import java.util.stream.Collectors


class CustomCollectors {


    companion object {

        /**
         * Provide a Collector for collecting values from a stream into a LinkedHashMap,
         * thus keeping the order.
         * @param keyMapper a mapping function to produce keys
         * @param valueMapper a mapping function to produce values
         * @return a Collector to collect values in a sorted map
         */
        @JvmStatic
        fun <T, K, U> toSortedMap(keyMapper: Function<in T, out K?>, valueMapper: Function<in T, out
        U?>): Collector<T, *, Map<K, U>> = Collectors.toMap(keyMapper,
            valueMapper,
            { u, _ -> throw IllegalStateException(String.format("Duplicate key %s", u)) },
            { LinkedHashMap() })

    }

}
