# -*- coding: utf-8 -*-
"""
Created on Sun May 31 21:31:48 2020

@author: Диана
"""
CHAR = 256
# Функция подсчета эвристики 
def Heuristic (string, size): 
    shift = [-1]*CHAR
    for i in range(size): 
        shift[ord(string[i])] = i; 
    return shift 
  
def BM(txt, pattern): 
    len_pat = len(pattern) # длина подстроки
    len_txt = len(txt) # длина текста
    badChar = Heuristic(pattern, len_pat)  
    s = 0
    while(s <= len_txt - len_pat): 
        j = len_pat-1
        while j>=0 and pattern[j] == txt[s+j]: 
            j -= 1
        if j<0: 
            print("index = {}".format(s)) 
  
            s += (len_pat - badChar[ord(txt[s + len_pat])] if s+len_pat < len_txt else 1) 
        else: 
            s += max(1, j-badChar[ord(txt[s+j])]) 
  
  
# Driver program to test above function 
def main(): 
    txt = "ABAAABCD"
    pat = "AB"
    BM(txt, pat) 
  
if __name__ == '__main__': 
    main() 
