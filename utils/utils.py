from datetime import datetime
import os
import matplotlib.pyplot as plt
import matplotlib.image as mpimg
import numpy as np
import tensorflow as tf
import csv


# Req. 2-2	세팅 값 저장
def save_config():
	pass


# Req. 4-1	이미지와 캡션 시각화
def visualize_img_caption(img_paths, caption):
	caption = open(img_paths + '/captions.csv', 'r', encoding='utf-8')
	caption_list = list(csv.reader(caption))
	caption_example = caption_list[2][0].split('|')
	caption_img_name = caption_example[0]
	caption_caption = caption_example[2].strip()

	img_path = img_paths + '/' + caption_img_name
	img = mpimg.imread(img_path)
	plt.title('<start> ' + caption_caption + ' <end>')
	plt.imshow(img)
	plt.show()
	return
