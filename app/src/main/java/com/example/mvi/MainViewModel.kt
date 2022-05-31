package com.example.mvi

/**
 * @author Ichvandi
 * Created on 30/05/2022 at 20:29.
 */
class MainViewModel : BaseViewModel<MainAction, MainState, MainEvent>() {

    override fun handleAction(action: MainAction) {
        when (action) {
            is MainAction.FirstAction -> handleFirstAction(action.text)
            MainAction.SecondAction -> handleSecondAction()
        }
    }

    private fun handleFirstAction(name: String) {
        if (name == "admin") {
            setState(MainState.FirstState("Admin"))
            setEvent(MainEvent.FirstEvent)
            return
        }

        setState(MainState.FirstState("User"))
        setEvent(MainEvent.FirstEvent)
    }

    private fun handleSecondAction() {
        setState(MainState.SecondState)
        setEvent(MainEvent.SecondEvent)
    }

}

sealed class MainAction {
    data class FirstAction(val text: String) : MainAction()
    object SecondAction : MainAction()
}

sealed class MainState {
    data class FirstState(val text: String) : MainState()
    object SecondState : MainState()
}

sealed class MainEvent {
    object FirstEvent : MainEvent()
    object SecondEvent : MainEvent()
}
