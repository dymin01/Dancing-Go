import argparse

# Req. 2-1	Config.py 파일 생성
def config():
    parser = argparse.ArgumentParser()
    parser.add_argument('--caption_file_path', type=str, default='.\\datasets\\captions.csv')
    parser.add_argument('--image_path', type=str, default='./datasets/images', help='path for image training data')
    parser.add_argument('--image_result_path', type=str, default='./datasets/images/result.csv', help='path for image training data')
    args = parser.parse_args()
    return args

def do_sampling():
    pass

# 캡션 데이터가 있는 파일 경로 (예시)
# parser.add_argument('--caption_file_path', type=str, default='.\\datasets\\captions.csv')
