package dev.mslalith.queues.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.queues.problems.DesignTaskManager.DesignTaskManagerParams
import dev.mslalith.queues.problems.DesignTaskManager.DesignTaskManagerType.Add
import dev.mslalith.queues.problems.DesignTaskManager.DesignTaskManagerType.Edit
import dev.mslalith.queues.problems.DesignTaskManager.DesignTaskManagerType.ExecTop
import dev.mslalith.queues.problems.DesignTaskManager.DesignTaskManagerType.Remove
import java.util.PriorityQueue

class DesignTaskManager : TestCaseProblem<DesignTaskManagerParams, List<Int?>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = DesignTaskManager().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<DesignTaskManagerParams, List<Int?>>> = arrayOf(
        TestCase(
            input = DesignTaskManagerParams(
                tasks = listOf(
                    listOf(1, 101, 10),
                    listOf(2, 102, 20),
                    listOf(3, 103, 15)
                ),
                queries = listOf(
                    Add(userId = 4, taskId = 104, priority = 5),
                    Edit(taskId = 102, priority = 8),
                    ExecTop,
                    Remove(taskId = 101),
                    Add(userId = 5, taskId = 105, priority = 15),
                    ExecTop,
                )
            ),
            output = listOf(null, null, 3, null, null, 5)
        )
    )

    override fun solve(testCaseInput: DesignTaskManagerParams): List<Int?> {
        val taskManager = TaskManager(tasks = testCaseInput.tasks)
        return testCaseInput.queries.map {
            when (it) {
                ExecTop -> taskManager.execTop()
                is Add -> {
                    taskManager.add(userId = it.userId, taskId = it.taskId, priority = it.priority)
                    null
                }
                is Edit -> {
                    taskManager.edit(taskId = it.taskId, newPriority = it.priority)
                    null
                }
                is Remove -> {
                    taskManager.rmv(taskId = it.taskId)
                    null
                }
            }

        }
    }

    private class TaskManager(
        tasks: List<List<Int>>
    ) {
        private val pq = PriorityQueue<Task>()
        private val taskIdToTaskMap = hashMapOf<Int, Task>()

        init {
            for ((userId, taskId, priority) in tasks) {
                add(userId, taskId, priority)
            }
        }

        fun add(userId: Int, taskId: Int, priority: Int) {
            val task = Task(userId, taskId, priority)
            taskIdToTaskMap[taskId] = task
            pq.add(task)
        }

        fun edit(taskId: Int, newPriority: Int) {
            val task = taskIdToTaskMap.remove(taskId) ?: return
            pq.remove(task)

            val newTask = task.copy(priority = newPriority)
            taskIdToTaskMap[taskId] = newTask
            pq.add(newTask)
        }

        fun rmv(taskId: Int) {
            val task = taskIdToTaskMap[taskId] ?: return
            pq.remove(task)
        }

        fun execTop(): Int {
            return if (pq.isEmpty()) -1 else pq.poll().userId
        }
    }

    data class Task(
        val userId: Int,
        val taskId: Int,
        val priority: Int
    ) : Comparable<Task> {
        override fun compareTo(other: Task): Int = if (priority != other.priority) other.priority - priority else other.taskId - taskId
    }

    data class DesignTaskManagerParams(
        val tasks: List<List<Int>>,
        val queries: List<DesignTaskManagerType>
    ) {
        override fun toString(): String {
            return """
                
                tasks: $tasks
                queries: $queries
            """.trimIndent()
        }
    }

    sealed interface DesignTaskManagerType {
        data class Add(val userId: Int, val taskId: Int, val priority: Int) : DesignTaskManagerType
        data class Edit(val taskId: Int, val priority: Int) : DesignTaskManagerType
        data class Remove(val taskId: Int) : DesignTaskManagerType
        data object ExecTop : DesignTaskManagerType
    }
}
