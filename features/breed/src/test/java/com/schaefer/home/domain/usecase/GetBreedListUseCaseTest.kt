package com.schaefer.home.domain.usecase

import com.schaefer.home.domain.repository.BreedRepository
import com.schaefer.home.stub.BreedStub
import io.mockk.coEvery
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetBreedListUseCaseTest {

    private val breedRepository: BreedRepository = mockk()
    private val getBreedListUseCase = GetBreedListUseCase(breedRepository)

    @Test
    fun `getBreedListUseCase Should return success with data When BreedRepository returns data`() =
        runBlockingTest {
            //Given
            coEvery {
                breedRepository.getBreedList()
            }.returns(
                Single.just(BreedStub.breedDomain)
            )

            //When
            val result = getBreedListUseCase().test()

            //Then
            result.assertComplete()
            result.assertNoErrors()
            result.assertValue(BreedStub.breedVO)
        }

    @Test
    fun `getBreedListUseCase Should return emptyList When BreedRepository fails`() =
        runBlockingTest {
            //Given
            coEvery {
                breedRepository.getBreedList()
            }.returns(
                Single.error(Throwable())
            )

            //When
            val result = getBreedListUseCase().test()

            //Then
            result.assertNotComplete()
            result.assertNoValues()
        }
}