#!/bin/bash

apt-get install --yes --force-yes clamav clamav-daemon clamav-freshclam
freshclam

echo 'TCPSocket 3310' >> /etc/clamav/clamd.conf
echo 'TCPAddr 127.0.0.1' >> /etc/clamav/clamd.conf

service clamav-daemon start

apt-get install --yes --force-yes clamfs
mkdir /etc/clamfs
mkdir /clamfs/tmp -p
chmod a+w /clamfs/tmp
echo \
'<?xml version="1.0" encoding="UTF-8"?>
<clamfs>
	<clamd socket="/var/run/clamav/clamd.ctl" check="yes" />
	<filesystem root="/tmp" mountpoint="/clamfs/tmp" public="yes" />
	<cache entries="16384" expire="10800000" />
	<log method="file" filename="/var/log/clamav/clamfs.log" verbose="yes" />
</clamfs>' \
 > /etc/clamfs/clamfs.conf
sed -i '/exit 0/i clamfs /etc/clamfs/clamfs.conf' /etc/rc.local

