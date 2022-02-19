Import('env')
import os
import rpmtool

name = env['pkg']
dest = env['destdir']
rel = env['release']
version = env['version']

# get all the files in the given directories (recursively)
lfile = rpmtool.get_files(['bin', 'etc'])

tgz = 'rpm/SOURCES/' + name + '-' + version + '.tar.gz'
env.Tar(tgz, lfile, TARFLAGS="--transform 's,^src/,,' -vcz")

mfl = []

for fl in lfile:
    mfl.append(dest + "/" + fl)

spec_dict = { '@VERSION@': version, '@REL@': rel, '@NAME@': name,
             '@SUM@': 'A basic rpm package', '@SRCFILES@' : ('\n').join(mfl),
             '@DESC@': 'Demonstrates RPM build with SCons', '@DEST@': dest }

specfile='rpm/SPECS/' + name + '.spec'
Depends(specfile, tgz)
env.Substfile('rpm/SPECS/' + name + '.in', SUBST_DICT=spec_dict, SUBSTFILESUFFIX='.spec')

arpm = 'rpm/RPMS/x86_64/' + name + '-' + version + '-' + rel + '.x86_64.rpm'

env.GetRPM(arpm, [specfile, tgz])
