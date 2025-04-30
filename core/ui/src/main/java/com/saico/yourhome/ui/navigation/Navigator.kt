package com.saico.yourhome.ui.navigation

import kotlinx.coroutines.flow.StateFlow

interface Navigator {
    /**
     * State flow that can be observed to consume the latest navigation command emitted, it only
     * should be used internally by [NavigatorHandler]
     */
    val commands: StateFlow<NavigationCommand>

    /**
     * This method is the main communication that components should use to communicate with the navigator,
     * it receives a command with the necessary information to perform a navigation action.
     *
     * @param command The navigation command that should be executed
     */
    fun navigate(command: NavigationCommand)

    /**
     * Internally used by the [NavigatorHandler] to cleanup the already consumed events
     */
    fun onNavigated()
}