# files-clamav-cloud-performance
A set of tests to measure performance of files IO and clamav in the cloud.

## Two types of tests

* Regular file system with clamfs - all access to disk goes through clamav transparently to the application
* Java NIO Jimfs with clamavj - everything happens in memory, thanks to using clamavj, scan requests are sent over TCP to clam-daemon process

Both versions of tests do two things:

* writing & reading 10k small files
* writing & reading 100 large files

Note: just for my own purposes in the `src/test/java` I have also added a few other tests. But of course I'm interested only in the results with clamav.

## Test results

Type of test | Environment | JUnit test name | Execution time
--- | --- | --- | :---: 
Regular file system with clamfs | Dell laptop + bare-metal Ubuntu 12.04 | RegularFileSystemClamfsTest | 9.326s
Java NIO Jimfs with clamavj | Dell laptop + bare-metal Ubuntu 12.04 JimfsFileSystemClamavjTest | 7.473s
Regular file system with clamfs | Macbook Pro + VirtualBox Ubuntu 12.04 | RegularFileSystemClamfsTest | 35.544s
Java NIO Jimfs with clamavj | MacBook Pro + VirtualBox Ubuntu 12.04 JimfsFileSystemClamavjTest | 11.504s

Comments:
* On bare-metal Ubuntu 12.04 the Java NIO Jimfs is slightly faster - 1.248.
* On VirtualBox Ubuntu 12.04 the Java NIO Jimfs is three times faster - 3.09.
