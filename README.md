# files-clamav-cloud-performance
A set of tests to measure performance of files (disk & memory) with clamav in the cloud.

## Two types of tests

* Regular file system with clamfs - all access to disk goes through clamav transparently to the application
* Java NIO Jimfs with clamavj - everything happens in memory, thanks to using clamavj, scan requests are sent over TCP to clam-daemon process

Both versions of tests do two things:

* writing & reading 10k small files (492 B)
* writing & reading 100 large files (1 MB)

Note: just for my own purposes in the `src/test/java` I have also added a few other tests. But of course I'm interested only in the results with clamav.

## Test results

All test systems were ran on Ubuntu 12.04

Test name | Machine | Execution time
--- | --- | :---: 
RegularFileSystemClamfsTest | Dell laptop - bare-metal | 11.320s
JimfsFileSystemClamavjTest | Dell laptop - bare-metal | 9.771s
RegularFileSystemClamfsTest | Macbook Pro - VirtualBox | 35.544s
JimfsFileSystemClamavjTest | MacBook Pro - VirtualBox | 11.504s
RegularFileSystemClamfsTest | AWS EC2 | 7.570s
JimfsFileSystemClamavjTest | AWS EC2 | 4.268s

Comments:
* on bare-metal Ubuntu 12.04 the Java NIO Jimfs is slightly faster - 1.16.
* on VirtualBox Ubuntu 12.04 the Java NIO Jimfs is three times faster - 3.09.
* on AWS EC2 Ubuntu 12.04 the Java NIO Jimfs is almost two times faster - 1.77.

The Java NIO Jimfs with clamavj wins in all three tests.


