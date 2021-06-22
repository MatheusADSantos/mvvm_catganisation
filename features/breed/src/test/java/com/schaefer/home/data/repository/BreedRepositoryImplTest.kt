package com.schaefer.home.data.repository

import com.schaefer.core.network.ResultWrapper
import com.schaefer.home.data.datasource.BreedRemoteDataSource
import com.schaefer.home.stub.BreedStub
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class BreedRepositoryImplTest {

    private val remoteDataSource: BreedRemoteDataSource = mockk()
    private val breedRepositoryImpl = BreedRepositoryImpl(remoteDataSource)

    @Test
    fun `getBreedList Should return success with data When remoteDataSource returns data`() =
        runBlockingTest {
            //Given
            every {
                remoteDataSource.getBreedList()
            }.returns(
                Single.just(BreedStub.breedResponse)
            )

            //When
            val result = breedRepositoryImpl.getBreedList().test()

            //Then
            result.assertComplete()
            result.assertNoErrors()
            result.assertValue(BreedStub.breedDomain)
        }

    @Test
    fun `getBreedList Should throw exception When remoteDataSource returns NetworkError`() =
        runBlockingTest {
            //Given
            val error = ResultWrapper.NetworkError(Exception())
            every {
                remoteDataSource.getBreedList()
            }.returns(
                Single.error(error.exception)
            )

            //When
            val result = breedRepositoryImpl.getBreedList().test()

            //Then
            result.assertError(error.exception)
            result.assertNotComplete()
            result.assertNoValues()
        }
}