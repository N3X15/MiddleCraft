import sys
import csv
import parsers
import yaml

#######################################################################################
# From MCP (create_dic_class, create_dic_member, parse_all_files), just modified to death.
#######################################################################################
def create_dic_class (parsed_rgs):
    return_dic = {}
    for entry in parsed_rgs['class_map']:
        notch_data = entry['src_name'].split('/')
        return_dic[entry['trg_name']] = {}                
        return_dic[entry['trg_name']]['notch']      = notch_data[-1]
        return_dic[entry['trg_name']]['searge']     = entry['trg_name']
        return_dic[entry['trg_name']]['full']       = entry['trg_name']
        return_dic[entry['trg_name']]['class']      = entry['trg_name']
        return_dic[entry['trg_name']]['full_final'] = entry['trg_name']
        return_dic[entry['trg_name']]['notch_pkg']  = '/'.join(notch_data[:-1])
        return_dic[entry['trg_name']]['modified']   = False
        return_dic[entry['trg_name']]['methods']    = {}
        return_dic[entry['trg_name']]['fields']    = {}

    return return_dic

def create_dic_member(parsed_rgs, parsed_csv, class_dict, target, type):
    return_dic = {}

    rev_class_dict = {}
    for key,value in class_dict.items():
        rev_class_dict[value['notch']] = key

    for entry in parsed_rgs[type]:
        
        notch_data = entry['src_name'].split('/')
        
        s_root = entry['trg_name']
        if entry['trg_name'].find('func') != -1 or entry['trg_name'].find('field') != -1:
            s_root = '_'.join(entry['trg_name'].split('_')[0:2])
        else:
            s_root = notch_data[-2] + '_' + entry['trg_name']
        
        
        
        return_dic[s_root] = {}
        return_dic[s_root]['notch']       = notch_data[-1]
        return_dic[s_root]['searge']      = entry['trg_name']
        return_dic[s_root]['s_root']      = s_root
        return_dic[s_root]['full']        = None
        return_dic[s_root]['full_final']  = None
        return_dic[s_root]['notch_sig']   = None
        return_dic[s_root]['csv']         = None
        return_dic[s_root]['index']       = s_root.split('_')[-1]
        return_dic[s_root]['known']       = False
        return_dic[s_root]['notch_class'] = notch_data[-2]
        return_dic[s_root]['notch_pkg']   = '/'.join(notch_data[:-2])
        return_dic[s_root]['class']       = None
        return_dic[s_root]['descript']    = None
        return_dic[s_root]['package']     = 'net/minecraft/server'
        
        #Bot related keys
        return_dic[s_root]['old_mod']     = False   #This modification has already been commited and is considered permanent
        return_dic[s_root]['new_mod']     = False   #This is a new modification to be commited on next update
        return_dic[s_root]['modified']    = False   #The entry has been modified
        return_dic[s_root]['nick_mod']    = None    #The name of the guy who did the last set on this entry
        return_dic[s_root]['time_mod']    = None    #The time of the modification
        return_dic[s_root]['forced']      = False   #If this entry has been forced modified
        return_dic[s_root]['annotation']  = ''      #Some infos which may be usefull later one
        
        try:
            return_dic[s_root]['class']       = rev_class_dict[notch_data[-2]]
        except:
            return_dic[s_root]['class']       = return_dic[s_root]['notch_class']
        
        if type == 'method_map':
            return_dic[s_root]['notch_sig']   = entry['src_sig']
        
    #We create a dict lookup based on the csv
    member_lookup = {}
    descri_lookup = {}
    for entry in parsed_csv:
        s_root = entry['searge_%s'%target]
        if entry['searge_%s'%target].find('func') != -1 or entry['searge_%s'%target].find('field') != -1:
            s_root = '_'.join(entry['searge_%s'%target].split('_')[0:2])        
        member_lookup[s_root] = entry['full']
        if 'description' in entry:
            descri_lookup[s_root] = entry['description']
        else:
            descri_lookup[s_root] = '*'

    #Now, we go through the return_dict, and associate the corresponding full name to the corresponding key
    #If we don't have a fullname, we 'star' it for later parsing
    known_name_repr   = 'csv'.split('+')
    unknown_name_repr = 'searge'.split('+')
    for part in known_name_repr:
        if part not in ['notch', 'searge', 's_root', 'csv', 'index']:
            raise KeyError("Unknown qualifier for representation. Choose in ['notch', 'searge', 's_root', 'csv', 'index'] separated by '+'")
    for part in unknown_name_repr:
        if part not in ['notch', 'searge', 's_root', 'csv', 'index']:
            raise KeyError("Unknown qualifier for representation. Choose in ['notch', 'searge', 's_root', 'csv', 'index'] separated by '+'")

    for key in return_dic.keys():
        try:
            return_dic[key]['csv']   = member_lookup[return_dic[key]['s_root']]
            return_dic[key]['known'] = True
        except KeyError:
            return_dic[key]['csv'] = return_dic[key]['s_root']

    for key in return_dic.keys():
        try:
            return_dic[key]['descript']   = descri_lookup[return_dic[key]['s_root']]
        except KeyError:
            return_dic[key]['descript']   = '*'
    
    for key in return_dic.keys():
        if return_dic[key]['known']:
            return_dic[key]['full']       = member_lookup[return_dic[key]['s_root']]
            return_dic[key]['full_final'] = '_'.join([return_dic[key][i] for i in known_name_repr])

        else:
            if return_dic[key]['searge'].find('func') != -1 or return_dic[key]['searge'].find('field') != -1:
                return_dic[key]['full']       = return_dic[key]['s_root']
                return_dic[key]['full_final'] = '_'.join([return_dic[key][i] for i in unknown_name_repr])
            else:
                return_dic[key]['full']       = return_dic[key]['searge']
                return_dic[key]['full_final'] = return_dic[key]['searge']

# Commented this part. It will make sure to have full final names with the notch extended, even if it is one of the enum.
# Not sure if it should be done or not.
#            if return_dic[key]['searge'].find('func') != -1 or return_dic[key]['searge'].find('field') != -1:
#                return_dic[key]['full']       = return_dic[key]['searge']
#                return_dic[key]['full_final'] = return_dic[key]['searge']
#            else:
#                return_dic[key]['full']       = return_dic[key]['searge']
#                return_dic[key]['full_final'] = return_dic[key]['searge'] + '_' + return_dic[key]['notch']
            
    return return_dic

def parse_all_files():
    class_csv  = parsers.parse_csv('data/mcp/classes.csv',  3, ',', ['full',      'trashbin', 'notch_c',  'trashbin',  'notch_s', 'description'])
    method_csv = parsers.parse_csv('data/mcp/methods.csv',  4, ',', ['trashbin',  'searge_c', 'trashbin', 'searge_s',  'full', 'description'])    
    field_csv  = parsers.parse_csv('data/mcp/fields.csv',   3, ',', ['trashbin',  'trashbin', 'searge_c', 'trashbin',  'trashbin', 'searge_s', 'full', 'description'])    
    
    #client_rgs = parsers.parse_rgs(config['client_rgs']) #contains a list of notch_name to searge_name for the client
    server_rgs = parsers.parse_rgs('data/mcp/minecraft_server.rgs') #contains a list of notch_name to searge_name for the server

    #We want 3 dicts per soft. One for classes, methods and fields. Each dict is going to take the searge_name as the key, as it is the only
    #unique identifier we are sure of for now. Each dict will have at least 3 entries, notch_name, searge_name and full_name.
    #Classes will have an identical searge_name and full_name, as they are identical. Methods will also contain the notch_signature and maybe the searge_signature.
    #Packages can also be a value somewhere for the reobfuscation step.

    #Let's start with the class dictionary. For this one, we just need the rgs file.
    #class_dict_c = create_dic_class(client_rgs)
    class_dict_s = create_dic_class(server_rgs)

    #Now the fields, as they are easy to process. Need both the csv and the rgs. Third argument is to get the right column
    #field_dict_c = create_dic_member(client_rgs, field_csv, class_dict_c, 'c', 'field_map', config)
    field_dict_s = create_dic_member(server_rgs, field_csv, class_dict_s, 's', 'field_map')

    #And finally the methods. Same as before.
    #method_dict_c = create_dic_member(client_rgs, method_csv, class_dict_c, 'c', 'method_map', config)
    method_dict_s = create_dic_member(server_rgs, method_csv, class_dict_s, 's', 'method_map')

    nmethods=0
    nfields=0
    nclasses=0
    ckeys=class_dict_s.keys()
    ckeys.sort()
    for ckey in ckeys:
        nclasses=nclasses+1
        #print '* Post-processing class %s...' % ckey
        for mkey in method_dict_s:
            method = method_dict_s[mkey]
            if method['class']==ckey:
                nmethods=nmethods+1
                nmkey = method['csv'] # Use CSV name to determine method key.
                if nmkey==None:
                    nmkey=method['searge']
                class_dict_s[ckey]['methods'][nmkey]=method
        for fkey in field_dict_s:
            field = field_dict_s[fkey]
            if field['class']==ckey:
                nfields=nfields+1
                nfkey = field['csv'] # Use CSV name to determine field key.
                if nfkey==None:
                    nfkey=field['searge']
                class_dict_s[ckey]['fields'][nfkey]=field
                
    print '*** POST-PROCESSING COMPLETE ***'
    print ' + %d classes' % nclasses
    print ' + %d methods' % nmethods
    print ' + %d fields' % nfields
    
    #solve_collisions(client_dic)
    #solve_collisions(server_dic)

    return class_dict_s

f = open('mappings.yml','w')
mcdict=parse_all_files()
print '+ Writing YAML mappings...'
yaml.dump(mcdict,f,default_flow_style=False)
#yaml.dump(parse_all_files(),f)
f.close()
    
