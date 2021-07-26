package com.idx.dataProducer.component

import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.stereotype.Component
import java.io.FileReader

@Component
internal class RandomClientData {

    private val csvMapper = CsvMapper().apply {
        registerModule(KotlinModule())
    }

    private val types: List<Any> =
            readCsvFile(this.javaClass.getResource(TYPES).file)

    private val names: List<Any> =
            readCsvFile(this.javaClass.getResource(NAMES).file)

    fun getTypes() = types

    fun getNames() = names

    private inline fun <reified T> readCsvFile(fileName: String): List<T> {
        FileReader(fileName).use { reader ->
            return csvMapper
                    .readerFor(T::class.java)
                    .with(CsvSchema.emptySchema().withHeader())
                    .readValues<T>(reader)
                    .readAll()
                    .toList()
        }
    }

    companion object {
        private const val TYPES = "/types.csv"
        private const val NAMES = "/names.csv"
    }
}