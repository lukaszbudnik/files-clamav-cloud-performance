# files-clamav-cloud-performance
A set of tests to measure performance of files IO and clamav in the cloud.

## Two types of tests

* Regular file system with clamfs - all access to disk goes through clamav transparently the the application
* Java NIO Jimfs with clamavj - everything happens in memory, thanks to using clamavj scan requests are sent over TCP to clam-daemon process

Note: just for my own purposes in the `src/test/java` I have also added tests for regular file system and jimfs without clamav. But of course I'm interested only in the results with clamav enabled.

## Running locally

Type of test | JUnit test name | Execution time
--- | --- | :---: 
Regular file system with clamfs | RegularFileSystemClamfsTest | 9.326s
Java NIO Jimfs with clamavj | JimfsFileSystemClamavjTest | 7.473s

Java NIO Jimfs is faster.
