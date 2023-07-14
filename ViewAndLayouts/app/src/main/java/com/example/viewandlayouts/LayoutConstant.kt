package com.example.viewandlayouts

enum class LayoutConstant {
    LINEAR_LAYOUT_CONSTANT {
        override val constant: Int
            get() = 0
    },
    RELATIVE_LAYOUT_CONSTANT {
        override val constant: Int
            get() = 1

    },
    GRID_LAYOUT_CONSTANT {
        override val constant: Int
            get() = 2

    },
    CONSTRAINT_LAYOUT_CONSTANT {
        override val constant: Int
            get() = 3

    };
    abstract val constant: Int

    companion object {
        fun newInstance(const: Int): LayoutConstant =
            when(const) {
                0 -> LINEAR_LAYOUT_CONSTANT
                1 -> RELATIVE_LAYOUT_CONSTANT
                2 -> GRID_LAYOUT_CONSTANT
                3 -> CONSTRAINT_LAYOUT_CONSTANT
                else -> throw IllegalArgumentException("There is no layout for this constant")
            }
    }
}