import numpy as np
import matplotlib.pyplot as plt

# setup
LINES_NUMBER = 50
LINES_AMPLITUDE = 7


def decel(x):
    return 1 - (x - 1) * (x - 1)


def get_ascii_lines_photo(image, shape, out_img, extension):
    height = shape[0]
    width = shape[1]
    for line in range(0, LINES_NUMBER):
        l = 0
        y = int(line * height / LINES_NUMBER)
        for x in range(0, width):
            m = np.max(image[y, x])
            m = 1 - m if m < 1 else 1 - m / 255
            for s in range(0, LINES_AMPLITUDE + 1):
                out_img[min(y + int(np.sin(l * np.pi / 2.) * LINES_AMPLITUDE * decel(m)), height - 1), x] = 0
                l += m / LINES_AMPLITUDE

    plt.imsave("./src/main/java/com/urfu/Tamada/command/ascii/ascii_lines_original.jpg", out_img)


def main(extension="jpg"):
    filename = "./src/main/java/com/urfu/Tamada/command/ascii/original.jpg"
    image = plt.imread(filename)
    shape = image.shape
    out_img = np.ones(shape)
    get_ascii_lines_photo(image, shape, out_img, extension)


if __name__ == "__main__":
    main()
