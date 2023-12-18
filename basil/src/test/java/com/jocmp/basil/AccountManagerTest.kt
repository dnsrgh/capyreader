package com.jocmp.basil

import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class AccountManagerTest {
    @JvmField
    @Rule
    var rootFolder = TemporaryFolder()

    private fun buildManager(): AccountManager {
        return AccountManager(
            rootFolder = rootFolder.newFolder().toURI(),
            preferencesProvider = InMemoryPreferencesProvider(AccountPreferences(displayName = "Local")),
            databaseProvider = InMemoryDatabaseProvider()
        )
    }

    @Test
    fun addAccount() {
        val manager = buildManager()

        assertNotNull(manager.createAccount())
        assertEquals(1, manager.accountSize())
    }

    @Test
    fun removeAccount() {
        val manager = buildManager()

        val account = manager.createAccount()
        manager.createAccount()

        manager.removeAccount(account.id)

        assertEquals(1, manager.accountSize())
    }

    @Test
    fun findById() {
        val manager = buildManager()

        val expectedAccount = manager.createAccount()

        val account = manager.findByID(expectedAccount.id)

        assertEquals(expectedAccount.id, account!!.id)
    }

    @Test
    fun findByIdMissingAccount() {
        val manager = buildManager()

        assertNull(manager.findByID("bogus"))
    }
}
