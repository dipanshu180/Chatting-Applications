import speech_recognition as sr
import pyttsx3
import webbrowser
import subprocess
import os   
import datetime
def say(text):
   en = pyttsx3.init()
   en.say(text)
   en.runAndWait()
   

def takeCommand():
    r = sr.Recognizer()     
    with sr.Microphone() as source:
        r.adjust_for_ambient_noise(source, duration=1)
        # r.pause_threshold =0.6
        audio = r.listen(source)
        try:
            query = r.recognize_google(audio , language="en-in")
            print(f"user said: {query}")
            return query
        except Exception as e:
            return "Some erroe occurred. Sorrry"
 
            
say("Hello deep")

while True:
    text = takeCommand()
    sites = [["YouTube","https://www.youtube.com"],["code", "https://leetcode.com/u/dipanshu_180"],["google","https://www.google.co.in"],["entertainment","D:\\entertainment"]]
    for site in sites:
        if f"{site[0]}".lower() in text.lower():
            say(f"Opening {site[0]} Deeep..")
            webbrowser.open(f"{site[1]}")
    if "open music" in text:
            say("music")
            musicPath =r"C:\Users\dipan\Downloads\Egzod, Maestro Chives, Neoni - Royalty [NCS Release].mp3"
            subprocess.run(["start", "", musicPath], shell=True)
    if "time" in text:
        time = datetime.datetime.now().strftime("%H:%M:%S")
        say(f" sir Deep time is {time}")
    
    if "stop" in text:
        say("Bye sir")
        exit(0)   
   
   

    