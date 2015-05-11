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

All systems were running Ubuntu 12.04

Test name | Machine | Execution time
--- | --- | :---: 
RegularFileSystemClamfsTest | Dell laptop - bare-metal | xxx
JimfsFileSystemClamavjTest | Dell laptop - bare-metal | xxx
RegularFileSystemClamfsTest | Macbook Pro - VirtualBox | 35.544s
JimfsFileSystemClamavjTest | MacBook Pro - VirtualBox | 11.504s
RegularFileSystemClamfsTest | AWS EC2 | 7.570s
JimfsFileSystemClamavjTest | AWS EC2 | 4.268s

Comments:
* on bare-metal Ubuntu 12.04 the Java NIO Jimfs is slightly faster - xxx.
* on VirtualBox Ubuntu 12.04 the Java NIO Jimfs is three times faster - 3.09.
* on AWS EC2 Ubuntu 12.04 the Java NIO Jimfs is almost two times faster - 1.774.
