from SCons.Script import *  # for Execute and Mkdir

rd =[]

def get_dirs(rd):
    ldir = []
    for root in rd:
        for start, dirs, files in os.walk(root):
            for dir in dirs:
                ldir.append(os.path.join(start, dir))
    return ldir
 

def get_files(rd):
    lfiles = []
    for root in rd:
        for start, dirs, files in os.walk(root):
            for file in files:
                lfiles.append(os.path.join(start, file))
    return lfiles
