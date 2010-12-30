import sys
import csv

class MethodInfo:
    realName=''
    name=''
    className=''
    mytype=''
        
class ClassInfo:
    header = "Real Name,MCP Name,Real Superclass,MiddleCraft Superclass,Description"
    name=''
    realName=''
    realSuperClass='java.lang.Object'
    superClass="*"
    description='*'
    def __init__(self):
        """Nothing, really."""
    def toString(self):
        return '%s,%s,%s,%s' % (self.realName,self.name,self.superClass,self.description)

methods=[]
                 
def importClasses(fileName,classNameColumn,obfuscatedColumn,descriptionColumn):
    classes=[]
    readFile = csv.reader(open(fileName, 'rb'))
    i=0
    for data in readFile:
        i=i+1
        if i==1:
            continue
        #print ', '.join(data)
        try:
            if data[obfuscatedColumn]!='':
                ci = ClassInfo()
                ci.name=data[classNameColumn]
                ci.realName=data[obfuscatedColumn]
                if descriptionColumn > -1:
                    try:
                        ci.description=data[descriptionColumn]
                    except:
                        ci.description="*"
                print " + %s (%s) - %s" % (ci.name,ci.realName,ci.description)
                classes.append(ci)
        except:
            print " ! ",', '.join(data)
    classes.sort()
    print "Saving..."
    writeFile = csv.writer(open('classes.csv','wb'))
    writeFile.writerow(['Real Name','MCP Name','Real Superclass','MiddleCraft Superclass','Description']);
    for c in classes:
        writeFile.writerow([c.realName,c.name,c.realSuperClass,c.superClass,c.description])
        
op=sys.argv[1]
if op=="classes":
    importClasses(sys.argv[2],int(sys.argv[3]),int(sys.argv[4]),int(sys.argv[5]))
else:
    print "%s classes infile classNameColumn obfuscatedColumn descriptionColumn|-1\n";
    
