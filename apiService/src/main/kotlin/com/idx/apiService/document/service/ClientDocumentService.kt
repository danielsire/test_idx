package com.idx.apiService.document.service

import com.idx.apiService.document.model.ClientDocument
import com.idx.apiService.document.model.INDEX
import com.idx.apiService.document.repository.ClientDocumentRepository
import com.idx.apiService.dto.ClientDto
import com.idx.apiService.dto.toDocument
import org.elasticsearch.common.unit.Fuzziness
import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.index.query.QueryBuilders.matchQuery
import org.springframework.data.domain.PageRequest
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.SearchHit
import org.springframework.data.elasticsearch.core.SearchHits
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder
import org.springframework.data.elasticsearch.core.query.Query
import org.springframework.stereotype.Service
import java.util.function.Consumer


@Service
internal class ClientDocumentService(
        private val clientDocumentRepository: ClientDocumentRepository,
        private val elasticsearchOperations: ElasticsearchOperations
) {

    fun findClientsByFirsName(firstName: String) {
        val queryBuilder: QueryBuilder = matchQuery("firstName", firstName)
        val searchQuery: Query = NativeSearchQueryBuilder()
            .withQuery(queryBuilder)
            .build()
        val clientHits: SearchHits<ClientDocument> = elasticsearchOperations.search(
                searchQuery,
                ClientDocument::class.java,
                IndexCoordinates.of(INDEX)
            )
    }

    fun findClientsByFirsNameAndLastName(name: String): MutableList<ClientDocument> {
        // 1. Create query on multiple fields enabling fuzzy search
        val queryBuilder: QueryBuilder = QueryBuilders
            .multiMatchQuery(name, "firstName", "lastName")
            .fuzziness(Fuzziness.AUTO)

        val searchQuery: Query = NativeSearchQueryBuilder()
            .withFilter(queryBuilder)
            .build()

        // 2. Execute search
        val clientHits: SearchHits<ClientDocument> = elasticsearchOperations
            .search(
                searchQuery, ClientDocument::class.java,
                IndexCoordinates.of(INDEX)
            )

        // 3. Map searchHits to product list
        val clientMatches: MutableList<ClientDocument> = ArrayList<ClientDocument>()
        clientHits.forEach(Consumer<SearchHit<ClientDocument?>> { searchHit: SearchHit<ClientDocument?> ->
            clientMatches.add(
                searchHit.content
            )
        })
        return clientMatches
    }

    fun save(client: ClientDto) {
        clientDocumentRepository.save(client.toDocument())
    }

    fun getByIsActive(isActive:Boolean) =
        clientDocumentRepository.findByIsActive(isActive, PageRequest.of(0, 30)).asSequence()
}
