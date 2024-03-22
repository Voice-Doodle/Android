package com.tes.vodle.repository

import com.tes.domain.model.Location
import com.tes.domain.model.Vodle
import com.tes.domain.repository.VodleRepository
import com.tes.vodle.datasource.vodle.VodleDataSource
import javax.inject.Inject

class VodleRepositoryImpl @Inject constructor(
    private val vodleDataSource: VodleDataSource
) : VodleRepository {
    override suspend fun fetchVodlesAround(): Result<List<Vodle>> =
        vodleDataSource.fetchVodlesAround().fold(
            onSuccess = { it ->
                Result.success(
                    it.dataBody.map {
                        Vodle(
                            it.id,
                            "",
                            it.address,
                            "",
                            it.category,
                            Location(it.latitude.toDouble(), it.longitude.toDouble())
                        )
                    }
                )
            },
            onFailure = { Result.failure(Exception()) }
        )
}
