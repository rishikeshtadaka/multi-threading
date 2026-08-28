

                                    +-------------------+

                                    |       NEW         |
                                    +---------+---------+
                                              |
                                              | start()
                                              v
                                    +-------------------+

                                    |     RUNNABLE      | <================+
                                    +----+---------+----+                  |

                                         |         ^                       |
            +----------------------------+         +------------+          |

            |                                                   |          |
            | Waiting for monitor lock                          |          | Thread gets lock /
            v                                                   v          | Notify / Time elapses
+-------------------+                                 +---------+--------+ |

|    BLOCKED        |                                 | WAITING /        | |
+-------------------+                                 | TIMED_WAITING    | |
                                                      +---------+--------+ |

                                                                |          |
                                                                +----------+
                                              |
                                              | Execution finishes
                                              v
                                    +-------------------+

                                    |    TERMINATED     |
                                    +-------------------+

The Java thread lifecycle consists of 6 distinct states defined by the Thread.State enum. This simple code demonstrates how a thread transitions through these states.
## State Transitions Demo

public class ThreadLifecycleDemo {
    public static void main(String[] args) throws InterruptedException {
        // Object used as an intrinsic lock for synchronization demo
        Object lock = new Object();

        // 1. NEW State: The thread is created but not yet started
        Thread thread = new Thread(() -> {
            try {
                // 3. TIMED_WAITING State: Thread sleeps for a specific duration
                System.out.println("3. State while sleeping: " + Thread.currentThread().getState());
                Thread.sleep(1000); 

                // 4. BLOCKED State: Attempting to enter a synchronized block locked by 'main'
                synchronized (lock) {
                    // 5. WAITING State: Waiting indefinitely for main thread to call notify()
                    System.out.println("5. State inside synchronized block (about to wait)...");
                    lock.wait(); 
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("1. State after creation: " + thread.getState());

        // 2. RUNNABLE State: The thread is ready to run or currently executing
        thread.start();
        System.out.println("2. State after start(): " + thread.getState());

        // Let the thread reach the Thread.sleep(1000) statement
        Thread.sleep(200);
        System.out.println("3. State from Main perspective (Sleeping): " + thread.getState());

        // Main thread acquires the lock so the background thread gets BLOCKED when it wakes up
        synchronized (lock) {
            // Wake up the thread from sleep by waiting out the 1000ms
            Thread.sleep(1000); 
            System.out.println("4. State from Main perspective (Blocked on lock): " + thread.getState());
        } // Main releases the lock here. Background thread enters the block and calls lock.wait()

        // Let the thread transition into lock.wait()
        Thread.sleep(200);
        System.out.println("5. State from Main perspective (Waiting on notify): " + thread.getState());

        // Wake the thread up from lock.wait()
        synchronized (lock) {
            lock.notify();
        }

        // Wait for the background thread to finish execution completely
        thread.join();
        
        // 6. TERMINATED State: The thread has finished executing its run method
        System.out.println("6. State after completion: " + thread.getState());
    }
}

## Explaining the 6 States

* NEW: A thread that has been instantiated but whose start() method has not yet been called.
* RUNNABLE: The thread is executing code in the JVM. It may be running, or it may be waiting for operating system resources like processor time allocation.
* TIMED_WAITING: The thread is waiting for another thread to perform an action for up to a specified waiting time. Triggered by methods like Thread.sleep(duration), Object.wait(duration), or Thread.join(duration).
* BLOCKED: The thread is waiting to acquire a monitor lock to enter a synchronized block or method because another thread is currently holding it.
* WAITING: The thread is waiting indefinitely for another thread to perform a specific action (like signaling). Triggered by calling Object.wait() without a timeout or Thread.join(). It will remain here until notify() or notifyAll() is explicitly called.
* TERMINATED: The thread has completed its execution, either because the run() method returned normally or an unhandled exception occurred.

If you want to move to the next logical concept, let me know if you would like to:

* Explain the difference between wait() and sleep()
* Show how to prevent a Deadlock situation using code
* Explain how Virtual Threads simplify lifecycle management in Java 21


