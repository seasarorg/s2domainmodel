#!/bin/sh

if [ -z $1 ];
then
	echo "enter a version to release."
	echo "> $0 <version>"
	exit
fi

mvn release:perform -DconnectionUrl=scm:svn:https://www.seasar.org/svn/sandbox/s2domainmodel/tags/s2-domainmodel-$1