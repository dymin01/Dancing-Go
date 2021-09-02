import os
import csv
import numpy as np
import pandas as pd
import pathlib
import shutil

# Req. 3-1	이미지 경로 및 캡션 불러오기
def get_path_caption():
    arr = list()
    arr.append('./datasets/images')
    path = pathlib.Path('./datasets/captions.csv')
    caption = pd.read_csv(path, encoding='cp949', sep='|')
    arr.append(caption)
    return


# Req. 3-2	전체 데이터셋을 분리해 저장하기
def dataset_split_save():
    caption = open('./datasets/captions.csv', 'r', encoding='cp949')
    caption_list = list(csv.reader(caption))
    caption_length = len(caption_list)
    test_length = caption_length * 8 // 10
    training_list, test_list = caption_list[1:test_length], caption_list[test_length:]
    f = open("./datasets/train/captions.csv", "w", encoding="cp949")
    f.write('image_name| comment_number| comment\n')
    for line in training_list:
        f.write(' '.join(line))
        f.write('\n')
        # img_name = line[0].split('|')[0]
        # shutil.copy("./datasets/images/" + img_name, './datasets/train/' + img_name)
    f = open("./datasets/test/captions.csv", "w", encoding="cp949")
    f.write('image_name| comment_number| comment\n')
    for line in test_list:
        f.write(' '.join(line))
        f.write('\n')
        # img_name = line[0].split('|')[0]
        # shutil.copy("./datasets/images/" + img_name, './datasets/test/' + img_name)
    return ['./datasets/train', './datasets/test']


# Req. 3-3	저장된 데이터셋 불러오기
def get_data_file(path):
    arr = list()
    arr.append(path)
    path = pathlib.Path(path + '/captions.csv')
    caption = pd.read_csv(path, encoding='cp949', sep='|')
    arr.append(caption)
    return arr


# Req. 3-4	데이터 샘플링
def sampling_data():
    return [1, 2]