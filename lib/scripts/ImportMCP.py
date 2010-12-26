import sys
import csv

class MethodInfo:
    realName=''
    name=''
    className=''
    mytype=''
        
class ClassInfo:
    name=''
    realName=''
    superClass='java.lang.Object'
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
                classes.append(ci.toString())
        except:
            print " ! ",', '.join(data)
    classes.sort()
    print "Saving..."
    writeFile = open('classes.csv','wb')
    writeFile.write("Real Name,MCP Name,Superclass,Description\n");
    for c in classes:
        writeFile.write(c)
        writeFile.write("\n")
    writeFile.close()
        
op=sys.argv[1]
if op=="classes":
    importClasses(sys.argv[2],int(sys.argv[3]),int(sys.argv[4]),int(sys.argv[5]))
else:
    print "%s classes infile classNameColumn obfuscatedColumn descriptionColumn|-1\n";
    
