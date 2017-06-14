from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions
import json
import time


_username = ''
_password = ''

print 'Starting'

driver = webdriver.PhantomJS('./bin/windows/phantomjs.exe')
#driver = webdriver.Chrome('./bin/windows/chromedriver.exe')
driver.set_window_size(1024, 768)

driver.get("https://test.iptv.bulsat.com/")
# Slow down for viewBox undefined error
time.sleep(3)
user = WebDriverWait(driver, 3).until(expected_conditions.element_to_be_clickable((By.ID, "user")))
user.send_keys(_username)
pwd = WebDriverWait(driver, 3).until(expected_conditions.element_to_be_clickable((By.ID, "pw")))
pwd.send_keys(_password)
print 'Logging in'
button = WebDriverWait(driver, 3).until(expected_conditions.presence_of_element_located((By.ID, "auth")))
button.click()


try:
    print 'Checking for errors'
    error = WebDriverWait(driver, 3).until(expected_conditions.presence_of_element_located((By.ID, 'error')))
    print 'Found Error'
    print error
except:
    print 'No Error'

try:
    print 'Getting playlist'
    WebDriverWait(driver, 10).until(expected_conditions.presence_of_element_located((By.ID, 'playlist')))
    data = driver.execute_script('return JSON.stringify(playlist)').encode('utf8')
    playlist = json.loads(data)
    print 'Got playlist with ' + len() + ' items.'
except:
    print 'Failed to load playlist'

driver.quit()
