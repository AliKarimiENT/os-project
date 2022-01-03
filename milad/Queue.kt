package com.ma

import java.io.File

class Queue {
    private var processList = mutableListOf<Process>()

    internal fun generateProcessList() {
        File("processList.txt").bufferedWriter().use { writer ->
            print("Writing queue file...\n")
            for (i in 1..100000) {
                writer.appendLine("Process: ${i}-${(0..10000).random()}-${(0..10000).random()}-${(0..10000).random()}")
            }
            print("Queue file written.")
        }
    }

    internal fun getReadyQueue(): List<Process> {
        File("processList.txt").bufferedReader().use { reader ->
            print("Reading queue file...\n")
            if (processList.isNotEmpty()) processList.clear()
            reader.forEachLine {
                val processSplitted = it.replace("Process: ", "").split("-")
                processList.add(Process(processSplitted[0].toInt(), processSplitted[1].toInt(), processSplitted[2].toInt(), processSplitted[3].toInt()))
            }
            print("Queue file Read.")
        }

        return processList.subList(0, 100)
    }

    internal fun completeReadyQueue(readyQueue: List<Process>): List<Process> {
        val currentQueue = readyQueue.toMutableList()
        val itemsCountToAdd = 100 - readyQueue.size
        print("Queue size before completion: ${currentQueue.size}\n")

        if (processList.isNotEmpty())
            for (i in 1..itemsCountToAdd) currentQueue.add(processList[i])
        else
            File("processList.txt").bufferedReader().use { reader ->
                print("Reading queue file...\n")
                var counter = 0
                reader.forEachLine {
                    counter += 1
                    val processSplitted = it.replace("Process: ", "").split("-")
                    if (counter <= itemsCountToAdd)
                        currentQueue.add(Process(processSplitted[0].toInt(), processSplitted[1].toInt(), processSplitted[2].toInt(), processSplitted[3].toInt()))

                }
                print("Queue file Read and the Queue is completed.\n")
            }

        print("Queue size after completion: ${currentQueue.size}")
        return currentQueue
    }
}