"""
3-7 설명: 단어들이 담긴 리스트에서 가장 긴 단어와 가장 짧은 단어를 찾는 프로그램을 작성하세요.
"""
word_list =  ['cat', 'elephant', 'dog', 'butterfly', 'ant']

long_word = word_list[0]
short_word = word_list[0]

long_len = len(long_word)   
short_len = len(short_word) 

for word in word_list:
    word_len = len(word)

    if word_len > long_len:
        long_word = word
        long_len = word_len

    if word_len < short_len:
        short_word = word
        short_len = word_len

print(f"가장 긴 단어: {long_word} ({long_len}글자)")
print(f"가장 짧은 단어: {short_word} ({short_len}글자)")
