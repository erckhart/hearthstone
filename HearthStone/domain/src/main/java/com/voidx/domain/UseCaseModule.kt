import com.voidx.domain.cases.FilterCardsUseCase
import com.voidx.domain.cases.ListGameInfoUseCase
import com.voidx.domain.cases.impl.FilterCardsUseCaseImpl
import com.voidx.domain.cases.impl.ListGameInfoUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {

    factory<ListGameInfoUseCase> {
        ListGameInfoUseCaseImpl(get())
    }

    factory<FilterCardsUseCase> {
        FilterCardsUseCaseImpl(get())
    }

}