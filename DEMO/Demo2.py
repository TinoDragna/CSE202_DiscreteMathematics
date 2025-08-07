import tkinter as tk
from tkinter import ttk
import serial
import threading
import time
import matplotlib.pyplot as plt
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg

# Cấu hình Serial
ser = serial.Serial()
ser.port = 'COM11'  # Thay bằng COM thực tế của bạn
ser.baudrate = 9600
ser.timeout = 1

# Dữ liệu cho biểu đồ
temperature_data = []
humidity_data = []
time_data = []

# Mở cổng Serial
def connect_serial():
    try:
        if not ser.is_open:
            ser.open()
            status_label.config(text="Status: Connected", foreground='green')
            threading.Thread(target=read_serial_data, daemon=True).start()
    except Exception as e:
        status_label.config(text="Connection failed", foreground='red')
        print(e)

# Đọc dữ liệu từ Arduino
def read_serial_data():
    t = 0
    while ser.is_open:
        try:
            line = ser.readline().decode().strip()
            if line.startswith("DHT:"):
                raw = line.replace("DHT:", "").strip()
                temp, hum = map(float, raw.split(','))
                temperature_data.append(temp)
                humidity_data.append(hum)
                time_data.append(t)
                t += 1
                if len(time_data) > 50:
                    temperature_data.pop(0)
                    humidity_data.pop(0)
                    time_data.pop(0)
                update_plot()
        except:
            continue
        time.sleep(1)

# Gửi lệnh LED
def send_command(command):
    if ser.is_open:
        ser.write((command + '\n').encode())

# Cập nhật biểu đồ
def update_plot():
    ax.clear()
    ax.plot(time_data, temperature_data, label='Temperature (°C)', color='tomato')
    ax.plot(time_data, humidity_data, label='Humidity (%)', color='steelblue')
    ax.set_ylim(0, 100)
    ax.set_xlabel('Time (s)')
    ax.set_ylabel('Value')
    ax.legend()
    canvas.draw()

# Giao diện Tkinter
root = tk.Tk()
root.title("Arduino LED + DHT11 Monitor")
root.geometry("800x600")
# --- Top Controls ---
top_frame = ttk.Frame(root)
top_frame.pack(pady=10)

port_label = ttk.Label(top_frame, text="Serial Port:")
port_label.pack(side=tk.LEFT)

port_combo = ttk.Combobox(top_frame, values=["COM11"], state="readonly")
port_combo.set("COM11")
port_combo.pack(side=tk.LEFT, padx=5)

connect_btn = ttk.Button(top_frame, text="Connect", command=connect_serial)
connect_btn.pack(side=tk.LEFT, padx=5)

status_label = ttk.Label(top_frame, text="Status: Disconnected", foreground='red')
status_label.pack(side=tk.LEFT, padx=10)

# --- LED Control ---
led_frame = ttk.Frame(root)
led_frame.pack(pady=10)

ttk.Label(led_frame, text="LED1:").pack(side=tk.LEFT, padx=5)
ttk.Button(led_frame, text="ON", command=lambda: send_command("LED1_ON")).pack(side=tk.LEFT)
ttk.Button(led_frame, text="OFF", command=lambda: send_command("LED1_OFF")).pack(side=tk.LEFT, padx=10)

ttk.Label(led_frame, text="LED2:").pack(side=tk.LEFT, padx=5)
ttk.Button(led_frame, text="ON", command=lambda: send_command("LED2_ON")).pack(side=tk.LEFT)
ttk.Button(led_frame, text="OFF", command=lambda: send_command("LED2_OFF")).pack(side=tk.LEFT)

# --- Biểu đồ ---
fig, ax = plt.subplots(figsize=(8, 4))
canvas = FigureCanvasTkAgg(fig, master=root)
canvas.get_tk_widget().pack()

# Khởi động Tkinter loop
root.mainloop()