import argparse
import sys
import os
from pathlib import Path
from PIL import Image, ImageFont, ImageDraw

GRAYSCALE_40 = r"B@&#MZWO0QbdpqwmoazunxrctiI1?l!+~;:-_,`."
GRAYSCALE_10 = '@%#J+=-:.'
GRAYSCALE_DOUBLE = '01'
SYMBOL_WIDTH, SYMBOL_HEIGHT = 11, 17
FONT = ImageFont.truetype(os.path.join(os.getcwd(), 'lucon.ttf'), 19)


def convertToAscii(image, new_width, new_height, symbols):
    image = image.resize((new_width, new_height))
    image = image.convert('L')
    pixels = image.getdata()
    interval = int(256 / len(symbols)) + 1
    new_pixels = ''.join([symbols[pixel // interval] for pixel in pixels])
    new_pixels_count = len(new_pixels)
    ascii_text = [new_pixels[index:index + new_width]
                  for index in range(0, new_pixels_count, new_width)]
    ascii_text = "\n".join(ascii_text)

    return ascii_text


def create_ascii_image(image, new_width, new_height, symbols):
    image = image.resize((new_width, new_height),
                         Image.NEAREST, reducing_gap=1.5)
    pixels = image.load()
    symbols = list(symbols[::-1])
    interval = len(symbols[::-1]) / 256
    ascii_image = Image.new(mode='RGB',
                            size=(new_width * SYMBOL_WIDTH,
                                  new_height * SYMBOL_HEIGHT),
                            color=(30, 30, 30))  # dark grey
    draw = ImageDraw.Draw(ascii_image)
    for i in range(new_height):
        for j in range(new_width):
            if isinstance(pixels[j, i], int):
                continue
            if len(pixels[j, i]) == 3:
                r, g, b = pixels[j, i]
            elif len(pixels[j, i]) == 4:
                r, g, b, d = pixels[j, i]
            shade_of_gray = int(r / 3 + g / 3 + b / 3)
            draw.text((j * SYMBOL_WIDTH, i * SYMBOL_HEIGHT),
                      (symbols[int(shade_of_gray * interval)]),
                      font=FONT, fill=(r, g, b))
    return ascii_image


def check_width_and_height(width, height):
    return width > 0 and height > 0


def main():
    img_path = os.path.join(os.getcwd(), 'original.jpg')
    path_image = img_path
    if os.path.exists(path_image):
        print("File exists")
        image = Image.open(path_image)
    else:
        print("NO")
        return
    width, height = image.size
    scale = 0.10
    new_width = int(width * scale)
    new_height = int(scale * height * (SYMBOL_WIDTH / SYMBOL_HEIGHT))
    out_file_name = os.path.join(os.getcwd(), 'ascii_string.txt')
    ascii_art = convertToAscii(image, new_width, new_height, GRAYSCALE_40)
    with open(out_file_name, "w") as f:
        f.write(ascii_art)
        print("File string has written")
    out_image = create_ascii_image(image, new_width, new_height, GRAYSCALE_40)
    pth = os.path.join(os.getcwd(), "ascii_original.jpg")
    out_image.save(pth)
    print("File out has written")


if __name__ == '__main__':
    main()
