LIB_DIR := $(CURDIR)/lib
INC_DIR := $(CURDIR)/include
LIB_LINUX := $(CURDIR)/lib/linux
LIB_OSX := $(CURDIR)/lib/osx
LIB_MINGW := $(CURDIR)/lib/mingw

VERSION ?= 4.5.0
CHECKSUM ?= 18a36b3e066f5743757cfa9ecbe784bbe20d529e

URL := https://github.com/raysan5/raylib/releases/download/
CHECKSUM_URL := https://raw.githubusercontent.com/raysan5/raylib/$(CHECKSUM)/src/

ARCHIVE_LINUX := raylib-$(VERSION)_linux_amd64.tar.gz
ARCHIVE_OSX := raylib-$(VERSION)_macos.tar.gz
ARCHIVE_MINGW := raylib-$(VERSION)_win64_mingw-w64.zip

ARCHIVE_LINUX_EXT := raylib-$(VERSION)_linux_amd64
ARCHIVE_OSX_EXT := raylib-$(VERSION)_macos
ARCHIVE_MINGW_EXT := raylib-$(VERSION)_win64_mingw-w64

HEADER_NAMES := raylib.h raymath.h rcamera.h

.PHONY: all
all: download extract download_headers

.PHONY: download
download:
	@echo "Downloading Raylib files..."
	@wget -q $(URL)$(VERSION)/$(ARCHIVE_LINUX)
	@wget -q $(URL)$(VERSION)/$(ARCHIVE_OSX)
	@wget -q $(URL)$(VERSION)/$(ARCHIVE_MINGW)

.PHONY: extract
extract:
	@echo "Extracting Raylib files..."
	@tar -C $(LIB_LINUX) -xf $(CURDIR)/$(ARCHIVE_LINUX) --strip-components=2 $(ARCHIVE_LINUX_EXT)/lib/libraylib.a
	@tar -C $(LIB_OSX) -xf $(CURDIR)/$(ARCHIVE_OSX) --strip-components=2 $(ARCHIVE_OSX_EXT)/lib/libraylib.a
	@unzip -q -j -d $(LIB_MINGW) $(CURDIR)/$(ARCHIVE_MINGW) $(ARCHIVE_MINGW_EXT)/lib/libraylib.a
	@unzip -q -j -d $(LIB_MINGW) $(CURDIR)/$(ARCHIVE_MINGW) $(ARCHIVE_MINGW_EXT)/lib/libraylibdll.a
	@echo "Cleaning up archive files..."
	@rm -rf $(CURDIR)/*.tar.gz $(CURDIR)/*.zip

.PHONY: download_headers
download_headers:
	@echo "Downloading header files..."
	@for header in $(HEADER_NAMES); do \
	    wget -q $(CHECKSUM_URL)$$header -P $(INC_DIR); \
	done

.PHONY: clean
clean:
	@echo "Cleaning up static libraries..."
	@if [ "$(OS)" == "Windows_NT" ]; then \
	    del /Q $(LIB_LINUX)\libraylib.a; \
	    del /Q $(LIB_OSX)\libraylib.a; \
	    del /Q $(LIB_MINGW)\libraylib.a; \
	    del /Q $(LIB_MINGW)\libraylibdll.a; \
	    del /Q $(INC_DIR)\raylib.h; \
	    del /Q $(INC_DIR)\rcamera.h; \
	    del /Q $(INC_DIR)\raymath.h; \
	else \
	    rm -f $(LIB_LINUX)/libraylib.a; \
	    rm -f $(LIB_OSX)/libraylib.a; \
	    rm -f $(LIB_MINGW)/libraylib.a; \
	    rm -f $(LIB_MINGW)/libraylibdll.a; \
	    rm -f $(INC_DIR)/raylib.h; \
	    rm -f $(INC_DIR)/rcamera.h; \
	    rm -f $(INC_DIR)/raymath.h; \
	fi
