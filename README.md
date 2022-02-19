SCons is a great build tool but the RPM package builder is not suited for production usage.

I had to build binary only RPM's but wasn't able to do this in an easy way with the builtin RPM builder. As with many RPM build tools one goal was to hide the complexities of RPM packages before the user which is good by intention. Problems arises if you have some special requirements and you cannot even adapt the spec file.

I therefore tried to use a more flexible approach. This requires some basic knowledge about the inner workings of RPM packages but gives you more control about the different build stages.

There is the main SConstruct file which defines a builder for the final build step with RPM build and another builder to create the RPM spec file from a template. The tar file which contains the sources for the RPM package is build with the internal Tar package builder from SCons.

This was used with SCons v4.3.0, python 3.6.8, rpmbuild 4.14.3 on Centos Stream 8 and RedHat 7.
If you just clone this repository and call

    scons

the RPM will be created as 

    rpm/RPMS/x86_64/mypkg-1.0.0-1.x86_64.rpm

It contains two empty files in /usr/local/{bin,etc}
