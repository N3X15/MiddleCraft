import sys
import csv

class MethodInfo:
    header = ("Real Name","Type","Parent Class","Readable Name","Description")
    realName=''
    seargeName=''
    name=''
    sig=''
    className=''
    description=''
    def __init__(self):
        """Nothing, really."""
        
class ClassInfo:
    header = ("Real Name","MCP Name","Real Superclass","MiddleCraft Superclass","Description")
    name=''
    realName=''
    realSuperClass='java.lang.Object'
    superClass="*"
    description='*'
    methods=[]
    fields=[]
    def __init__(self):
        """Nothing, really."""
    def toString(self):
        return '%s,%s,%s,%s' % (self.realName,self.name,self.superClass,self.description)

def parse_rgs   (filename):
    
    print '+ Opening file %s for reading.'%filename
    ff = open(filename, 'rb')

    rgs_keywords = {'.option':    ['rgs_name'], 
                    '.class_map': ['src_name', 'trg_name'], 
                    '.method_map':['src_name', 'src_sig', 'trg_name'], 
                    '.field_map': ['src_name', 'trg_name'],                    
                    '.class':     ['src_name', 'arg'],
                    '.field':     ['src_name', 'src_type', 'arg'],
                    '.method':    ['src_name', 'src_sig',  'arg'],
                    '.attribute': ['src_name', 'arg']
                    }

    parsed_dic   = {'option':[],
                    'class_map':[],
                    'method_map':[],
                    'field_map':[],
                    'class':[],
                    'field':[],
                    'method':[],
                    'attribute':[]
                    }

    def get_parsed_line(keyword, buffer):
        return dict(zip(rgs_keywords[keyword], [i.strip() for i in buffer]))


    while True:
        
        buffer = ff.readline()
        if not buffer:
            break
 
        buffer = buffer.split(' ')
        #If we have a comment, we skip
        if buffer[0][0] == '#' or buffer[0].strip() == '':
            continue
            
        #If we have a known keyword
        if buffer[0] in rgs_keywords:
            parsed_dic[buffer[0][1:]].append(get_parsed_line(buffer[0], buffer[1:]))
        else:
            raise KeyError('Error parsing RGS file %s. Keyword %s not recognized.'%(filename, buffer[0]))
 
    ff.close()
    
    return parsed_dic

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
    return classes


def importMethods(classes):
    methods=[]
    """
    IMPORT RGS SHIT
    .method_map net/minecraft/server/MinecraftServer/d ()Z func_6008_d
    """
    method_map_s = parse_rgs("data/minecraft_server.rgs")
    """
    IMPORT METHOD NAMES
                          0 1     2           3          4         5
    Usually laid out like *,*,CLASS_NAME,SEARGE_NAME,FUNC_NAME,FUNC_DESC
    """
    CLASS_NAME=2
    SEARGE_NAME=3
    FUNC_NAME=4
    FUNC_DESC=5
    readFile = csv.reader(open("data/methods.csv", 'rb'))
    i=0
    for data in readFile:
        i=i+1
        if data[0]=='NULL':
            continue
        #print ', '.join(data)
        try:
            if data[2]!='*':
                index = next((i for i in xrange(len(classes) - 1, -1, -1) if classes[i].name == data[2]), None)
                ci=classes[index]
                
                for method in method_map_s['method_map']:
                    mi = MethodInfo()
                    mi.className='.'.join(method['src_name'].split('/')[:-1])
                    mi.seargeName=method['trg_name']
                    mi.realName=method['src_name'].split('/')[-1]
                    mi.sig=method['src_sig']                        
                    if mi.seargeName==data[SEARGE_NAME]:
                        mi.name=data[FUNC_NAME]
                        mi.description=data[FUNC_DESC]
                        print " + %s.%s (%s)" % (mi.className,mi.name,mi.realName)
                        methods.append(mi)
        except:
            print " ! ",', '.join(data)
    methods.sort()
    print "Saving..."
    writeFile = csv.writer(open('methods.csv','wb'))
    writeFile.writerow(["Real Name","Type","Parent Class","Readable Name","Description"]);
    for c in methods:
        writeFile.writerow([c.realName,c.sig,c.className,c.name,c.description])
    
        
op=sys.argv[1]
classes=importClasses(sys.argv[2],0,4,5)
methods=importMethods(classes)
    
