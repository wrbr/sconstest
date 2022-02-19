import os

vars = Variables()

vars.Add('pkg', 'Set package name', "mypkg")
vars.Add('destdir', 'Set destination directory', "/usr/local")
vars.Add('version', 'Set version', "1.0.0")
vars.Add('release', 'Set release', "1")
vars.Add('verbose', 'Set verbosity for rpmbuild (y/n)', "n")
env = Environment(TARFLAGS = "-c -z --wildcards", variables = vars)


# Set verbosity

if env['verbose'] == "n":
    env['verbose'] = "--quiet"
else:
    env['verbose'] = ""
 

# define the working directory for rpmbuild
act = Dir('.')
topd = act.get_abspath()
env['TDIR'] = topd + '/rpm'

# builder function for rpmbuild

def build_rpm(source, target, env):
    stream = os.popen('rpmbuild -bb %s --define "_topdir %s" %s' % (source[0], env['TDIR'], env['verbose']))
    outp = stream.read()
    print(outp)
    return None


bld = Builder(action=build_rpm)
env.Append(BUILDERS={'GetRPM': bld})


if env.get('pkg'):
    print ("Building " + env['pkg'])
    SConscript("src/" + env['pkg']  + '.sc', exports = 'env')
else:
    print ('\nUsage: scons pkg=<package name> version=<version>')
    exit(1)
