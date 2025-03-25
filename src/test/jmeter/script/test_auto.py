# coding=utf-8
import requests
import time
import json
import sys
import hashlib
from lxml import etree

HEADERS = {'ua': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.125 Safari/537.36'}
DINGDING_URL= 'https://oapi.dingtalk.com/robot/send?access_token=af60bd5dcef4e4bcc281c6d20af07becae42c6086bae882e8722535d6eba2271'
RESULT_FILE= 'target/jmeter/html/test11.html'
REPORT_URL_FILE= 'http://192.168.248.124:8080/job/test_auto/HTML_20Report/'

class Message:

    def __init__(self):
        self.total=0
        self.passed=0
        self.failed=0
        self.reportUrl=""


    def analyze(self):
        try:
            retail_file = open(RESULT_FILE,"r")
            f = retail_file.read()
            html = etree.HTML(f)
            self.total = html.xpath('string(//table[2]/tr[2]/td[1]/text())')
            self.passed = html.xpath('string(//table[2]/tr[2]/td[2]/text())')
            self.failed = html.xpath('string(//table[2]/tr[2]/td[3])')
            self.SuccessPercent = html.xpath('string(//table[2]/tr[2]/td['
                                             '4]/text())')
            #file = open(REPORT_URL_FILE,'r')
            self.reportUrl=REPORT_URL_FILE
        except:
            print ("read file error")

    def send_message_to_robot(self):
        url= DINGDING_URL
        message='test_auto自动化执行结果:\n本次执行了{}条用例;\n成功了{}条;\n失败了{}条;\n成功率：{' \
                '};\n查看详情请点击=> ' \
                '{}'.format(self.total,self.passed,self.failed,self.SuccessPercent,
                            self.reportUrl)
        data={"msgtype":"text","text":{"content":message,"title":"用户经营自动化结果通知"}}
        try:
            resp = requests.post(url,headers=HEADERS,json=data,timeout=(3,60))
        except:
            print ("Send Message is fail!")

if __name__ == '__main__':
    message = Message()
    message.analyze()
    message.send_message_to_robot()